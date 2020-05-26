import { Moment } from 'moment';
import { ILocation } from 'app/shared/model/location.model';
import { IContact } from 'app/shared/model/contact.model';
import { IDeal } from 'app/shared/model/deal.model';
import { Gender } from 'app/shared/model/enumerations/gender.model';

export interface ICustomer {
  id?: number;
  customerName?: string;
  customerGender?: Gender;
  customerBirthDate?: Moment;
  locations?: ILocation[];
  contacts?: IContact[];
  deal?: IDeal;
}

export class Customer implements ICustomer {
  constructor(
    public id?: number,
    public customerName?: string,
    public customerGender?: Gender,
    public customerBirthDate?: Moment,
    public locations?: ILocation[],
    public contacts?: IContact[],
    public deal?: IDeal
  ) {}
}
