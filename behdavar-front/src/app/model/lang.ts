export class Lang {
  save = 'ذخیره';
  insert = 'افزودن';
  new = 'جدید';
  edit = 'ویرایش';
  search = 'جستجو';
  view = 'مشاهده';
  reset = 'بازنشانی'
  appTitle = 'بهداور';
  logout = 'خروج';
  row = 'ردیف';
  back = 'بازگشت';
  successSave = 'عملیات با موفقیت انجام شد';
  error = 'خطا در انجام عملیات';
  delete = 'حذف';
  active = 'فعال';
  inactive = 'غیر فعال';
  title = 'عنوان';
  send = 'ارسال';
  description = 'توضیحات';
  errorOnSearch = 'خطا در جستجو';
  rial = 'ریال';
  date = 'تاریخ';
  time = 'زمان';
  exit = 'خروج';
}

export class AuthLang extends Lang {
  usernameNotFound = 'نام کاربری یافت نشد';
  incorrectUsernameOrPassword = 'رمز عبور اشتباه است';
  other = 'خطا در احراز هویت';
}

export class LoginLang extends Lang {
  loginMessage = 'ورود به سیستم';
  usernameMessage = 'نام کاربری';
  passwordMessage = 'رمز عبور';
  enterMessage = 'ورود';
  userNameRequired = 'نام کاربری اجباری است';
  passwordRequired = 'رمز عبور اجباری است';
  loginTitle = 'نرم افزار جامع پیگیری وصول مطالبات';

}

export class PersonLang extends Lang {
  name = 'نام و نام خانوادگی';
  firstName = 'نام';
  lastName = 'نام خانوادگی';
  fatherName = 'نام پدر';
  birthDate = 'تاریخ تولد';
  nationalNumber = 'کد ملی';
  postalCode = 'کد پستی';
  mobile = 'تلفن همراه';
  telephone = 'تلفن';
  birthPlace = 'محل تولد';
  workPlacePhone = 'تلفن محل کار';
}

export class HomeLang extends Lang {
  myBasket = 'سبد من';
  documentManagement = 'مدیریت پرونده ها';
  reports = 'گزارشات';
  utilityTools = 'ابزارهای کاربردی';
  userManagement = 'مدیریت کاربران';
  documentInput = 'ورود پرونده';
}

export class ChangeExpertLang extends Lang {
  title = 'تغییر کارشناس پرونده';
  documentNumber = 'شماره پرونده';
  referToExpert = 'ارجاع به کارشناس';
  newExpert = 'انتخاب کارشناس جدید';
  changeExpertHistory = 'سابقه وضعیت کارشناسان پرونده';

  firstName = 'نام';
  lastName = 'نام خانوادگی';
  date = 'تاریخ';
  errorOnSearchExpert = 'خطا در واکشی لیست کارشناسان';
  newExpertRequired = 'یک کارشناس انتخاب کنید';


}

export class DocumentInputLang extends Lang {
  manual = 'دستی';
  auto = 'خودکار';
  chooseFile = 'انتخاب فایل';
}

export class UserManagementLang extends Lang {
  users = 'کاربران';
  roles = 'نقش ها';
}

export class UserRegistrationLang extends PersonLang {
  userList = 'لیست کاربران';
  username = 'نام کاربری';
  password = 'رمز عبور';
  code = 'کد';
  userRoles = 'نقش های کاربر';
  personRole = 'انتخاب فرد جدید';
  selectRole = 'انتخاب نقش جدید';
  errorOnSearchUser = 'خطا در جستجو کاربران';
  errorOnSearchPerson = 'خطا در جستجو فرد';
  errorOnFindRole = 'خطا در جستجو نقش';
  personNotNull = 'فرد اجباری است';
  userRoleNotEmpty = 'لیست نقش ها نمی تواند خالی باشد';
  usernameNotValid = 'نام کاربری معتبر نیست';
  passwordNotValid = 'رمزعبور معتبر نیست';
  codeNotValid = 'کد معتبر نیست';
  selectAUserFromList = 'یک کاربر را از لیست انتخاب کنید';

}

export class RoleRegistrationLang extends Lang {
  role = 'نقش';
  roleList = 'لیست نقش ها';
  roleName = 'نام نقش';
  privilegeList = 'لیست دسترسی ها';
  privilegeName = 'نام دسترسی';
  privilegeTitle = 'عنوان دسترسی';
  selectPrivilege = 'انتخاب دسترسی جدید';
  errorOnFindPrivilege = 'خطا در جستجو دسترسی';
  createNewRole = 'ایجاد نقش جدید';
  rolePrivileges = 'دسترسی ها';
  remainingPrivileges = 'دسترسی ها موجود';
  roleNameNotValid = 'نام نقش صحیح نمی باشد';
  roleTitleNotValid = 'عنوان نقش را وارد کنید';
  roleTitle = 'عنوان';
  rolePrivilegesNotBeEmpty = 'لیست دسترسی ها نباید خالی باشد';
  editRole = 'ویرایش نقش';
  selectAPrivilege = 'هیج دسترسی انتخاب نشده، حداقل یک دسترسی را از لیست انتخاب کنید';

}

export class DocumentToolbarLang extends Lang {
  followUp = 'پیگیری';
  guarantorInfo = 'اطلاعات ضامنین';
  customerInfo = 'اطلاعات مشتری';
  contents = 'ضمائم';
  financialSituation = 'وضعیت مالی';
  changeStatus = 'تغییر وضعیت پرونده';
  projectFlow = 'گردش پرونده';
  changeExpert = 'تغییر کارشناس';
}

export class DocumentChangeStatusLang extends Lang {
  documentNumber = 'شماره پرونده';
  applyChange = 'ثبت تغییرات';
  documentStatusHistory = 'سوابق وضعیت پرونده';
  status = 'وضعیت';
  selectDocumentStatus = 'انتخاب وضعیت پرونده';
  pageTitle = 'تغییر وضعیت پرونده';

}

export class UtilityToolsLang extends Lang {
  link_118 = 'وب سایت سامانه ۱۱۸';
  link_insurance = 'وب سایت سامانه استعلام بیمه';
  link_map = 'گوگل مپ';
  link_saipa = 'وب سایت سامانه رایان سایپا';
  link_behdavar = 'شرکت بهداور اندیش عدالت';
}

export class DocumentLang extends PersonLang {
  bankMachine = 'بانک/ماشین';
  document = 'پرونده';
  customer = 'مشتری';
  customerName = 'نام مشتری';
  facilityNumber = 'شماره تسهیلات';
  status = 'وضعیت پرونده';
  lateFees = 'جریمه دیرکرد';
  deferredAmount = 'مبلغ معوق (ریال)';
  deferredCount = 'تعداد معوق';
  totalAmount = 'مجموع تسهیلات';
  registrationDate = 'تاریخ ثبت پرونده';
  bank = 'بانک';
  branch = 'شعبه';
  facilityReceivingDate = 'تاریخ دریافت تسهیلات';
  plateNumber = 'پلاک خودرو';
  vehicleType = 'نوع خودرو';
  expert = 'کارشناس';
  ideaIssueDate = 'تاریخ صدور رای';
  receiveLendingDate = 'تاریخ دریافت تسهیلات'
  tableTitle = 'لیست پرونده ها';
  searchPanel = 'پنل جستجو';
}

export class FollowingLang extends Lang {
  projectFollowing = 'پیگیری پرونده';
  followingType = 'نوع پیگیری';
  date = 'تاریخ';
  time = 'ساعت';
  expertName = 'نام کارشناس';
  followingResult = 'نتیجه پیگیری';
  followingResultDescription = 'شرح نتیجه پیگیری';
  depostidAmount = 'مبلغ واریز شده مشتری';
  coordinateAppointment = 'قرار ملاقات هماهنگ شد';
  customerDepositAmount = 'مشتری واریز داشته است';
  depositAppointment = 'قرار واریز هماهنگ شد';
  submitAcordingToFinalAction = 'ثبت به عنوان گزارش آخرین اقدام';
  attachedBrowse = 'بارگذاری ضمائم یا فیش واریز';
  nextFollowingDate = 'تاریخ یادآوری پیگیری بعدی';
  tableTitle = 'لیست پیگیری های قبلی';

}

export class GuarantorsLang extends PersonLang {
  guarantorsInformation = 'اطلاعات ضامنین';
  guarantorList = 'لیست ضامنین';
  contactList = 'اطلاعات تماس';
  selectAGuarantor = 'یک ضامن را از لیست انتخاب کنید';
}

export class CustomerLang extends PersonLang {
  customerInformation = 'اطلاعات مشتری';
  contactList = 'اطلاعات تماس';
}

export class ContactLang extends Lang {
  number = 'شماره تماس';
  preCode = 'کد';
  confirmed = 'تایید شده';
  phoneType = 'نوع';
  selectOneItem = 'یک مورد از لیست تماس را انتخاب کنید';
}

export class PhoneTypeLang extends Lang {
  phone = 'تلفن ثابت';
  mobile = 'شماره همراه';
  fax = 'فکس';
}

export class AttachmentLang extends Lang {
  documentAttachment = 'ضمائم پرونده';
  documentNumber = 'شماره پرونده';
  attachDocument = 'آپلود مدرک';
  documentList = 'لیست مدارک';
  titleDocument = 'عنوان مدرک';
  fileDocument = 'فایل مدرک';
  chooseFile = 'انتخاب فایل';
  attachDocumentHistory = 'لیست مدارک ضمیمه شده';


}

export class FinancialStatusLang extends Lang {
  pageTitle = 'وضعیت مالی';
  totalAmount = 'مبلغ کل پرونده';
  receiveAmount = 'مبلغ کل واریز شده';
  paymentList = 'لیست و اطلاعات واریزی های مشتری';
  amount = 'مبلغ (ریال)';
  paymentType = 'نوع دریافت';
  expert = 'کارشناس';
  contractStatus = 'وضعیت پرونده';
  paymentDate = 'تاریخ';
  tableTitle = 'لیست واریزی های مشتری';
}

export class DocumentFlowLang extends Lang {
  pageTitle = 'گردش پرونده';
  contractNumber = 'شماره پرونده';
  flow = 'گردش';
  previousExpert = 'کارشناس قبلی';
  newExpert = 'کارشناس جدید';
  referralUnit = 'واحد ارجاع دهنده';
  date = 'تارخ';
  time = 'ساعت';
  tableTitle = 'لیست گردش پرونده';

}

export class PursuitTypeLang extends Lang {
  PHONE_CALL = "تماس تلفنی";
  SEND_SMS = "ارسال پیامک";
  SEND_NOTICE = 'ارسال اخطاریه کتبی';
}

export class ResultTypeLang extends Lang {
  REQUEST_TIME = 'درخواست زمان';
  NO_ANSWER = 'عدم پاسخ';
  FULL_SETTLEMENT = 'تسویه کامل';
  LEGAL_REFERENCE = 'ارجاع به حقوقی';
}

export class ContractStatusLang extends Lang {
  AVAILABLE = 'در دسترس';
  CLEARING = 'پاک شده';
  RAW = 'خام';
  LEGAL = 'مجاز';
  PARKING = 'توقیف وسیله';
  RETURN = 'برگشت داده شده';
}

export class PaymentTypeLang extends Lang {
  CHECK = 'چک';
  CASH = 'نقدی';
  NOTEBOOK = 'دفترچه';
  OTHER = 'سایر';
  INSTALLMENT_LOCATION_CASH = 'پرداخت در محل';
  INSTALLMENTS = 'قسطی';
}

export class ReportsLang extends Lang {
  reportTitle = 'گزارش گیری';
  fromDate = 'از تاریخ';
  toDate = 'تا تاریخ';
  groupByPeriod = 'به تفکیک دوره';
  groupByDocumentType = 'به تفکیک نوع پرونده';
  groupByDocumentStatus = 'به تفکیک وضعیت پرونده';
  period = 'دوره';
  documentType = 'نوع پرونده';
  documentStatus = 'وضعیت پرنده';
  countOfFollowing = 'گزارش تعداد و متن پیگیری های انجام شده';
  amountReceived = 'گزارش مبلغ مالی وصول شده';
  countOfDocument = 'گزارش تعداد پرونده وصول شده';
  oneMonth = 'ماهانه';
  trimester = 'سه ماهه';
  sixMonth = 'شش ماهه';
  yearly = 'سالانه';
  bank = 'بانک';
  machine = 'ماشین';
  available = 'در دسترس';
  clearing = 'تسویه (ارجاع به طرح و برنامه)';
  raw = 'پرونده خام';
  legal = 'حقوقی (ارجاع حقوقی)';
  parking = 'پارکینگی';
  comeback = 'بازگشت';
  groupBy = 'به تفکیک';
  report = 'تهیه گزارش';


}
