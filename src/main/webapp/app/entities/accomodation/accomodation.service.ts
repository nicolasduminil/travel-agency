import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAccomodation } from 'app/shared/model/accomodation.model';

type EntityResponseType = HttpResponse<IAccomodation>;
type EntityArrayResponseType = HttpResponse<IAccomodation[]>;

@Injectable({ providedIn: 'root' })
export class AccomodationService {
  public resourceUrl = SERVER_API_URL + 'api/accomodations';

  constructor(protected http: HttpClient) {}

  create(accomodation: IAccomodation): Observable<EntityResponseType> {
    return this.http.post<IAccomodation>(this.resourceUrl, accomodation, { observe: 'response' });
  }

  update(accomodation: IAccomodation): Observable<EntityResponseType> {
    return this.http.put<IAccomodation>(this.resourceUrl, accomodation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAccomodation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAccomodation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
