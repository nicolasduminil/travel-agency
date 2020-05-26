import { ILocation } from 'app/shared/model/location.model';
import { IActivity } from 'app/shared/model/activity.model';
import { ICustomer } from 'app/shared/model/customer.model';
import { IDeal } from 'app/shared/model/deal.model';
import { Salutation } from 'app/shared/model/enumerations/salutation.model';

export interface IContact {
  id?: number;
  contactName?: string;
  contactFirstName?: string;
  contactLastName?: string;
  contactEmailAddress?: string;
  contactWebSite?: string;
  contactSalutation?: Salutation;
  contactJobTitle?: string;
  contactPhoneNumber?: string;
  contactFaxNumber?: string;
  address?: ILocation;
  activity?: IActivity;
  customer?: ICustomer;
  deal?: IDeal;
}

export class Contact implements IContact {
  constructor(
    public id?: number,
    public contactName?: string,
    public contactFirstName?: string,
    public contactLastName?: string,
    public contactEmailAddress?: string,
    public contactWebSite?: string,
    public contactSalutation?: Salutation,
    public contactJobTitle?: string,
    public contactPhoneNumber?: string,
    public contactFaxNumber?: string,
    public address?: ILocation,
    public activity?: IActivity,
    public customer?: ICustomer,
    public deal?: IDeal
  ) {}
}
