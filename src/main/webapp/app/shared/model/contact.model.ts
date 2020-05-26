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
  addressId?: number;
  activityId?: number;
  customerId?: number;
  dealId?: number;
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
    public addressId?: number,
    public activityId?: number,
    public customerId?: number,
    public dealId?: number
  ) {}
}
