class BaseModel<T> {
  id: T;
  version ?: number;
}


export class Guarantors extends BaseModel<number>{
  person:Person;
}

export class Person {
  name:string;
  family:string;
  fatherName:string;
  birthDate:Date;
  nationalNumber:string;
  postalCode:string;
  mobile:string;
  telephone:string;
  birthPlace:string;
  workPlacePhone:string;
}
