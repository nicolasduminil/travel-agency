import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITransport } from 'app/shared/model/transport.model';

type EntityResponseType = HttpResponse<ITransport>;
type EntityArrayResponseType = HttpResponse<ITransport[]>;

@Injectable({ providedIn: 'root' })
export class TransportService {
  public resourceUrl = SERVER_API_URL + 'api/transports';

  constructor(protected http: HttpClient) {}

  create(transport: ITransport): Observable<EntityResponseType> {
    return this.http.post<ITransport>(this.resourceUrl, transport, { observe: 'response' });
  }

  update(transport: ITransport): Observable<EntityResponseType> {
    return this.http.put<ITransport>(this.resourceUrl, transport, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITransport>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITransport[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
