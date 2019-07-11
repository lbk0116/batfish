parser grammar CiscoNxos_common;

options {
  tokenVocab = CiscoNxosLexer;
}

bgp_asn
:
  large = uint32
  | high = uint16 PERIOD low = uint16
;

cisco_nxos_password
:
// Lexer mode change required prior to entering this rule
  PASSWORD_0? text = PASSWORD_0_TEXT
  | PASSWORD_3
  (
    text = PASSWORD_3_TEXT
    | malformed = PASSWORD_3_MALFORMED_TEXT
  )
  | PASSWORD_7
  (
    PASSWORD_7_TEXT
    | malformed = PASSWORD_7_MALFORMED_TEXT
  )
;

interface_address
:
  address = ip_address mask = subnet_mask
  | iaddress = ip_prefix
;

interface_name
:
  prefix = interface_prefix middle = interface_middle? parent_suffix =
  interface_parent_suffix? first = uint16
;

interface_prefix
:
  ETHERNET
  | LOOPBACK
  | MGMT
  | PORT_CHANNEL
  | VLAN
;

interface_middle
:
  (
    uint8 FORWARD_SLASH
  )+
;

interface_parent_suffix
:
  num = uint16 period = PERIOD
;

ip_address
:
  IP_ADDRESS
  | SUBNET_MASK
;

ip_prefix
:
  IP_PREFIX
;

ipv6_address
:
  IPV6_ADDRESS
;

ipv6_prefix
:
  IPV6_PREFIX
;

line_action
:
  deny = DENY
  | permit = PERMIT
;

null_rest_of_line
:
  ~NEWLINE* NEWLINE
;

prefix_list_name
:
// 1-63 chars
  WORD
;

route_map_name
:
// 1-63 chars
  WORD
;

route_network
:
  address = ip_address mask = subnet_mask
  | prefix = ip_prefix
;

subdomain_name
:
  SUBDOMAIN_NAME
;

subnet_mask
:
  SUBNET_MASK
;

template_name
:
// 1-80 chars
  WORD
;

track_object_number
:
// 1-512
  UINT8
  | UINT16
;

uint8
:
  UINT8
;

uint16
:
  UINT8
  | UINT16
;

uint32
:
  UINT8
  | UINT16
  | UINT32
;

vlan_id
:
// 1-4094
  UINT8
  | UINT16
;

vlan_id_range
:
  intervals += vlan_id_interval
  (
    COMMA intervals += vlan_id_interval
  )*
;

vlan_id_interval
:
  low = vlan_id
  (
    DASH high = vlan_id
  )?
;

vni_number
:
// 0-16777215
  UINT8
  | UINT16
  | UINT32
;

vrf_name
:
  WORD
;