package org.batfish.question.boolean_expr.iface;

import org.batfish.question.Environment;
import org.batfish.question.interface_expr.InterfaceExpr;
import org.batfish.representation.Interface;
import org.batfish.representation.IsisInterfaceMode;

public final class IsisL1ActiveInterfaceBooleanExpr extends
      InterfaceBooleanExpr {

   public IsisL1ActiveInterfaceBooleanExpr(InterfaceExpr caller) {
      super(caller);
   }

   @Override
   public Boolean evaluate(Environment environment) {
      Interface iface = _caller.evaluate(environment);
      return iface.getIsisL1InterfaceMode() == IsisInterfaceMode.ACTIVE;
   }

}
