export default class Url {
  public static readonly BASE_URL = "http://localhost:8081";
  public static readonly BASE_API = Url.BASE_URL + '/api';
  public static readonly LOGIN = `${Url.BASE_URL}/auth/login`;

  public static readonly CARTABLE_FIND_PAGING = `${Url.BASE_API}/cartable/find-paging`;
  public static readonly PAYMENT_FIND_BY_CONTRACT = `${Url.BASE_API}/payment/find-by-contract`;
  public static readonly GUARANTOR_FIND_BY_ID = `${Url.BASE_API}/guarantor/find-by-id`;
  public static readonly GUARANTOR_FIND_BY_CONTRACT = `${Url.BASE_API}/guarantor/find-by-contract`;

  public static readonly CUSTOMER_FIND_BY_CONTRACT = `${Url.BASE_API}/customer/find-by-contract`;

  public static readonly PURSUIT_FIND_BY_CONTRACT = `${Url.BASE_API}/pursuit/find-by-contract`;

  public static readonly PURSUIT_FIND_PAGING = `${Url.BASE_API}/pursuit/find-paging`;

  public static readonly PURSUIT_SAVE = `${Url.BASE_API}/pursuit/save`;

  public static readonly PERSON_UPDATE = `${Url.BASE_API}/person/update`;
  public static readonly PERSON_FIND_SUGGESTION = `${Url.BASE_API}/person/find-suggestion`;

  public static readonly USER_FIND_PAGING = `${Url.BASE_API}/user/find-paging`;
  public static readonly USER_FIND_BY_ID = `${Url.BASE_API}/user/find-by-id`;
  public static readonly USER_SAVE = `${Url.BASE_API}/user/save`;
  public static readonly USER_DELETE = `${Url.BASE_API}/user/delete`;

  public static readonly ROLE_FIND_BY_ID = `${Url.BASE_API}/role/find-by-id`;
  public static readonly ROLE_FIND_PAGING = `${Url.BASE_API}/role/find-paging`;
  public static readonly ROLE_SAVE = `${Url.BASE_API}/role/save`;
  public static readonly ROLE_UPDATE = `${Url.BASE_API}/role/update`;
  public static readonly ROLE_FIND_SUGGESTION = `${Url.BASE_API}/role/find-suggestion`;

  public static readonly PRIVILEGE_FIND_PAGING = `${Url.BASE_API}/privilege/find-paging`;
  public static readonly PRIVILEGE_FIND_SUGGESTION = `${Url.BASE_API}/privilege/find-suggestion`;
}
