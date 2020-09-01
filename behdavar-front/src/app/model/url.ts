export default class Url {
  public static readonly BASE_URL = "http://localhost:8081";
  public static readonly BASE_API = Url.BASE_URL + '/api';
  public static readonly LOGIN = `${Url.BASE_URL}/auth/login`;

  public static readonly CARTABLE_FIND_PAGING = `${Url.BASE_API}/cartable/find-paging`;
  public static readonly GUARANTOR_FIND_BY_ID = `${Url.BASE_API}/guarantor/find-by-id`;
  public static readonly GUARANTOR_FIND_BY_CONTRACT = `${Url.BASE_API}/guarantor/find-by-contract`;

  public static readonly CUSTOMER_FIND_BY_CONTRACT = `${Url.BASE_API}/customer/find-by-contract`;
}
