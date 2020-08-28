export default class Url {
  public static readonly BASE_URL = "http://localhost:8081";
  public static readonly LOGIN = `${Url.BASE_URL}/auth/login`;
  public static readonly CATALOG_FIND_PAGING = `${Url.BASE_URL}/catalog/find-paging`;
}
