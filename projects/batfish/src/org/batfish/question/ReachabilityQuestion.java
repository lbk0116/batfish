package org.batfish.question;

import java.util.EnumSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.batfish.common.BatfishException;
import org.batfish.representation.IcmpCode;
import org.batfish.representation.IcmpType;
import org.batfish.representation.Prefix;
import org.batfish.representation.TcpFlags;
import org.batfish.util.SubRange;

public class ReachabilityQuestion extends Question {

   private Set<ForwardingAction> _actions;

   private Set<SubRange> _dstPortRange;

   private Set<Prefix> _dstPrefixes;

   private Pattern _finalNodeRegex;

   private int _icmpCode;

   private int _icmpType;

   private Pattern _ingressNodeRegex;

   private Set<SubRange> _ipProtocolRange;

   private Set<SubRange> _srcPortRange;

   private Set<Prefix> _srcPrefixes;

   private int _tcpFlags;

   public ReachabilityQuestion(QuestionParameters parameters) {
      super(QuestionType.REACHABILITY, parameters);
      _actions = EnumSet.noneOf(ForwardingAction.class);
      // default action-- may change
      _actions.add(ForwardingAction.ACCEPT);

      _dstPortRange = new TreeSet<SubRange>();
      _dstPrefixes = new TreeSet<Prefix>();
      _ipProtocolRange = new TreeSet<SubRange>();
      _srcPortRange = new TreeSet<SubRange>();
      _srcPrefixes = new TreeSet<Prefix>();
      _icmpType = IcmpType.UNSET;
      _icmpCode = IcmpCode.UNSET;
      _tcpFlags = TcpFlags.UNSET;
   }

   public Set<ForwardingAction> getActions() {
      return _actions;
   }

   @Override
   public boolean getDataPlane() {
      return true;
   }

   @Override
   public boolean getDifferential() {
      return false;
   }

   public Set<SubRange> getDstPortRange() {
      return _dstPortRange;
   }

   public Set<Prefix> getDstPrefixes() {
      return _dstPrefixes;
   }

   public Pattern getFinalNodeRegex() {
      return _finalNodeRegex;
   }

   public int getIcmpCode() {
      return _icmpCode;
   }

   public int getIcmpType() {
      return _icmpType;
   }

   public Pattern getIngressNodeRegex() {
      return _ingressNodeRegex;
   }

   public Set<SubRange> getIpProtocolRange() {
      return _ipProtocolRange;
   }

   public Set<SubRange> getSrcPortRange() {
      return _srcPortRange;
   }

   public Set<Prefix> getSrcPrefixes() {
      return _srcPrefixes;
   }

   public int getTcpFlags() {
      return _tcpFlags;
   }

   public void setFinalNodeRegex(String regex) {
      try {
         _finalNodeRegex = Pattern.compile(regex);
      }
      catch (PatternSyntaxException e) {
         throw new BatfishException(
               "Supplied regex for final node is not a valid java regex: \""
                     + regex + "\"", e);
      }
   }

   public void setIcmpCode(int icmpCode) {
      _icmpCode = icmpCode;
   }

   public void setIcmpType(int icmpType) {
      _icmpType = icmpType;
   }

   public void setIngressNodeRegex(String regex) {
      try {
         _ingressNodeRegex = Pattern.compile(regex);
      }
      catch (PatternSyntaxException e) {
         throw new BatfishException(
               "Supplied regex for ingress node is not a valid java regex: \""
                     + regex + "\"", e);
      }
   }

   public void setTcpFlags(int tcpFlags) {
      _tcpFlags = tcpFlags;
   }

}
