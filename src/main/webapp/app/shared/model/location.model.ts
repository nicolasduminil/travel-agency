export interface ILocation {
  id?: number;
  streetAddress?: string;
  streetNumber?: string;
  postalCode?: string;
  city?: string;
  stateProvince?: string;
  countryName?: string;
  accomodationId?: number;
  contactId?: number;
  activityId?: number;
  transportToId?: number;
  transportFromId?: number;
  customerId?: number;
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
    public accomodationId?: number,
    public contactId?: number,
    public activityId?: number,
    public transportToId?: number,
    public transportFromId?: number,
    public customerId?: number
  ) {}
}
