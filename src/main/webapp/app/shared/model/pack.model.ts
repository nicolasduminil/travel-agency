import { IService } from 'app/shared/model/service.model';

export interface IPack {
  id?: number;
  packageName?: string;
  packageDescription?: string;
  packageDiscount?: number;
  packagePrice?: number;
  services?: IService[];
}

export class Pack implements IPack {
  constructor(
    public id?: number,
    public packageName?: string,
    public packageDescription?: string,
    public packageDiscount?: number,
    public packagePrice?: number,
    public services?: IService[]
  ) {}
}
