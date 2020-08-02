export class Lang {
  save = 'ذخیره';
  search = 'جستجو';
  reset = 'بازنشانی'
  appTitle = 'بهداور';
  logout = 'خروج';
}

export class PersonLang  extends Lang{
  name='نام';
  family='نام خانوادگی';
  fatherName = "نام پدر";
  birthDate = "تاریخ تولد";
  nationalNumber = "کد ملی";
  postalCode = "کد پستی";
  mobile = "تلفن همراه";
  telephone = "تلفن";
  birthPlace = "محل تولد";
  workPlacePhone = "تلفن محل کار";
}

export class HomeLang extends Lang {
  myBasket = 'سبد من';
  reports = 'گزارشات';
  utilityTools = 'ابزارهای کاربردی';
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

export class DocumentSearchLang extends Lang {
  bankMachine = 'بانک/ماشین';
  document = 'پرونده';
  customer = 'مشتری';
}

export class DocumentSearchFormLang extends DocumentSearchLang {
  facilityNumber = 'شماره تسهیلات';
  status = 'وضعیت پرونده';
  registrationDate = 'تاریخ ثبت پرونده';
}

export class CustomerSearchFormLang extends PersonLang {
  customer = 'مشتری';
}

export class BankMachineSearchFormLang extends DocumentSearchLang {
  bank = 'بانک';
  branch = 'شعبه';
  facilityReceivingDate = 'تاریخ دریافت تسهیلات';
  plateNumber = 'پلاک خودرو';
  vehicleType = 'نوع خودرو';
}

export class GuarantorsLang extends PersonLang {
  guarantorsInformation='اطلاعات ضامنین';
}

export class CustomerLang extends PersonLang {
  customerInformation = 'اطلاعات مشتری';
}

