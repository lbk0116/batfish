package org.batfish.dataplane.rib;

import static org.batfish.dataplane.ibdp.TestUtils.annotateRoute;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.batfish.datamodel.AbstractRoute;
import org.batfish.datamodel.AnnotatedRoute;
import org.batfish.datamodel.Prefix;
import org.batfish.datamodel.StaticRoute;
import org.junit.Test;

/** Tests of {@link Rib} */
public class RibTest {
  @Test
  public void testCreatedEmpty() {
    assertThat(new Rib().getRoutes(), empty());
  }

  @Test
  public void testNonRoutingIsNotInstalled() {
    Rib rib = new Rib();
    AnnotatedRoute<AbstractRoute> route =
        annotateRoute(
            StaticRoute.testBuilder()
                .setNextHopInterface("foo")
                .setNetwork(Prefix.ZERO)
                .setAdministrativeCost(1)
                .setNonRouting(true)
                .build());

    assertThat(rib.mergeRouteGetDelta(route), equalTo(RibDelta.empty()));
    assertThat(rib.mergeRoute(route), equalTo(false));
  }

  @Test
  public void testComparePreferenceAdmin() {
    Rib rib = new Rib();
    // Identical routes, different admin distance.
    StaticRoute.Builder sb =
        StaticRoute.testBuilder().setNextHopInterface("foo").setNetwork(Prefix.ZERO);
    AbstractRoute route1 = sb.setAdministrativeCost(100).build();
    AbstractRoute route2 = sb.setAdministrativeCost(101).build();

    assertThat(rib.comparePreference(annotateRoute(route1), annotateRoute(route2)), greaterThan(0));
    assertThat(rib.comparePreference(annotateRoute(route2), annotateRoute(route1)), lessThan(0));
  }

  @Test
  public void testComparePreferenceAdminEqual() {
    Rib rib = new Rib();
    // Identical routes should be equally preferred
    StaticRoute.Builder sb =
        StaticRoute.testBuilder()
            .setNextHopInterface("foo")
            .setNetwork(Prefix.ZERO)
            .setAdministrativeCost(100);
    AbstractRoute route1 = sb.build();
    AbstractRoute route2 = sb.build();

    assertThat(rib.comparePreference(annotateRoute(route1), annotateRoute(route2)), equalTo(0));
  }
}
