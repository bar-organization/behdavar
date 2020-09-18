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
  title = 'عنوان';
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

}

export class PersonLang extends Lang {
  name = 'نام';
  family = 'نام خانوادگی';
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
  reports = 'گزارشات';
  utilityTools = 'ابزارهای کاربردی';
  userManagement= 'مدیریت کاربران';
}

export class UserManagementLang extends Lang{
  users = 'کاربران';
  roles = 'نقش ها';
}
export class UserRegistrationLang extends Lang{
  userList = 'لیست کاربران';
  username = 'نام کاربری';
  password = 'رمز عبور';
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
  selectAUserFromList = 'یک کاربر را از لیست انتخاب کنید';

}
export class RoleRegistrationLang extends Lang{
  role = 'نقش';
  roleList = 'لیست نقش ها';
  roleName= 'نام نقش';
  privilegeList = 'لیست دسترسی ها';
  privilegeName= 'نام دسترسی';
  selectPrivilege = 'انتخاب دسترسی جدید';
  errorOnFindPrivilege = 'خطا در جستجو دسترسی';
  createNewRole = 'ایجاد نقش جدید';
  rolePrivileges = 'دسترسی ها';
  roleNameNotValid = 'نام نقش صحیح نمی باشد';
  roleTitleNotValid = 'عنوان نقش را وارد کنید';
  roleTitle = 'عنوان';
  rolePrivilegesNotBeEmpty = 'لیست نقش ها نباید خالی باشد';
  editRole = 'ویرایش نقش';
}
export class DocumentToolbarLang extends Lang {
  followUp = 'پیگیری';
  guarantorInfo = 'اطلاعات ضامنین';
  customerInfo = 'اطلاعات مشتری';
  contents = 'ضمائم';
  financialSituation = 'وضعیت مالی';
  changeStatus = 'تغییر وضعیت پرونده';
  projectFlow = 'گردش پرونده';
}

export class DocumentLang extends PersonLang {
  bankMachine = 'بانک/ماشین';
  document = 'پرونده';
  customer = 'مشتری';
  customerName = 'نام مشتری';
  facilityNumber = 'شماره تسهیلات';
  status = 'وضعیت پرونده';
  lateFees = 'جریمه دیرکرد';
  deferredAmount = 'مبلغ معوق';
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
}

export class FollowingLang extends Lang {
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

}

export class GuarantorsLang extends PersonLang {
  guarantorsInformation = 'اطلاعات ضامنین';
  guarantorList = 'لیست ضامنین';
}

export class CustomerLang extends PersonLang {
  customerInformation = 'اطلاعات مشتری';
}

export class AttachmentLang extends Lang {
  documentAttachment = 'ضمائم پرونده';
  documentNumber = 'شماره پرونده';
  attachDocument = 'آپلود مدرک';
  documentList = 'لیست مدارک';
  titleDocument = 'عنوان مدرک';
  fileDocument = 'فایل مدرک';
}

export class FinancialStatusLang extends Lang{
  totalAmount = 'مبلغ کل پرونده';
  receiveAmount = 'مبلغ کل واریز شده';
  paymentList = 'لیست و اطلاعات واریزی های مشتری';
  amount = 'مبلغ';
  paymentType = 'نوع دریافت';
  expert = 'کارشناس' ;
  contractStatus = 'وضعیت پرونده';
  paymentDate = 'تاریخ' ;
}
export class PursuitTypeLang extends Lang {
  PHONE_CALL = "تماس تلفنی";
  APPOINTMENT = 'قرار ملاقات';
  NOTICE = "اعلان";
  PAYMENT = "پرداخت";
  REFER_PROPERTY = "مراجعه به ملک";
  OTHER = "سایر موارد";
  INQUIRY_BRANCH = "واحد تحقیق";
  RECEIPT_POST_FAX = "دریافت از فکس";
  SEND_SMS = "ارسال پیامک";
  REVIEW_DOCUMENT = "بررسی سند";
  REQUEST_DOCUMENT = "درخواست سند";
  PEACE_AND_RECONCILIATION = "صلح و سازش";
  LETTER_HELP = "نامه کمکی";
  LEGAL_REVIEW = "بررسی حقوقی";
  REFER_LAWYER = "مراجعه به وکیل";
  PREPARING_REFER_LAWYER = "آماده مراجعه به وکیل";
  REQUEST_RELIGION_DOCUMENTS = "درحواست سند دین";
  SEND_LETTER = "ارسال نامه";
  EXECUTIVE_REGISTRATION_LETTER = "نامه ثبت نام اجرایی";
  NOTIFICATION_RECEIVE = "دریافت اعلان";
  REGISTRATION_EXECUTIVE_CLASS = "ثبت نام کلاس اجرایی";
  PROPERTY_IDENTIFICATION = "شناسایی ملک";
  MISSION = "ماموریت";
  REPRESENTATIVE_REFER = "مرجع نمایندگی";
  REQUEST_SEIZURE = "درخواست توقیف";
  IVR = "تلفن گویا";
  UPDATED = "به روز شده"
}

export class ResultTypeLang extends Lang {
  RETURNED = "برگردانده شده";
  NOT_ANNOUNCED = "اعلام نشده است";
  COLLECTION = "مجموعه";
  RETURN = "برگشت داده شده";
  GUARANTEE = "ضمانت";
  CONVERSION = "تبدیل";
  DURATION = "مدت زمان";
  ACCEPTED = "پذیرفته شده";
  DAY_CHECK = "بررسی روز";
  SEND_TO_BANK = "ارسال به بانک";
  SEND_TO_FINANCE = "ارسال به امور مالی";
  SEND_TO_BRANCH = "ارسال به شعبه";
  SEND_TO_AREA = "ارسال به منطقه";
  NON_APPROVAL_AREA = "منطقه تصویب نشده";
  FINAL_CONFIRMATION = "تائید نهایی";
  RENEWAL_BANK_RESOURCES = "تجدید منابع بانک";
}
