package org.batfish.question.ip_expr.bgp_advertisement;

import org.batfish.question.Environment;
import org.batfish.question.bgp_advertisement_expr.BgpAdvertisementExpr;
import org.batfish.representation.BgpAdvertisement;
import org.batfish.representation.Ip;

public final class NextHopIpBgpAdvertisementIpExpr extends
      BgpAdvertisementIpExpr {

   public NextHopIpBgpAdvertisementIpExpr(BgpAdvertisementExpr caller) {
      super(caller);
   }

   @Override
   public Ip evaluate(Environment environment) {
      BgpAdvertisement caller = _caller.evaluate(environment);
      return caller.getNextHopIp();
   }

}
