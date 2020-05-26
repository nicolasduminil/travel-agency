import { IAccomodation } from 'app/shared/model/accomodation.model';
import { IContact } from 'app/shared/model/contact.model';
import { IActivity } from 'app/shared/model/activity.model';
import { ITransport } from 'app/shared/model/transport.model';
import { ICustomer } from 'app/shared/model/customer.model';

export interface ILocation {
  id?: number;
  streetAddress?: string;
  streetNumber?: string;
  postalCode?: string;
  city?: string;
  stateProvince?: string;
  countryName?: string;
  accomodation?: IAccomodation;
  contact?: IContact;
  activity?: IActivity;
  transport?: ITransport;
  transport?: ITransport;
  customer?: ICustomer;
}

export class Location implements ILocation {
  constructor(
    public id?: number,
    public streetAddress?: string,
    public streetNumber?: string,
    public postalCode?: string,
    public city?: string,
    public stateProvince?: string,
    public countryName?: string,
    public accomodation?: IAccomodation,
    public contact?: IContact,
    public activity?: IActivity,
    public transport?: ITransport,
    public transport?: ITransport,
    public customer?: ICustomer
  ) {}
}
