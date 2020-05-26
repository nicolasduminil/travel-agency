import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ServiceService } from 'app/entities/service/service.service';
import { IService, Service } from 'app/shared/model/service.model';

describe('Service Tests', () => {
  describe('Service Service', () => {
    let injector: TestBed;
    let service: ServiceService;
    let httpMock: HttpTestingController;
    let elemDefault: IService;
    let expectedResult: IService | IService[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ServiceService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Service(0, 'AAAAAAA', currentDate, currentDate, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            serviceStartDate: currentDate.format(DATE_FORMAT),
            serviceEndDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Service', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            serviceStartDate: currentDate.format(DATE_FORMAT),
            serviceEndDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            serviceStartDate: currentDate,
            serviceEndDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Service()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Service', () => {
        const returnedFromService = Object.assign(
          {
            serviceDescription: 'BBBBBB',
            serviceStartDate: currentDate.format(DATE_FORMAT),
            serviceEndDate: currentDate.format(DATE_FORMAT),
            servicePrice: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            serviceStartDate: currentDate,
            serviceEndDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Service', () => {
        const returnedFromService = Object.assign(
          {
            serviceDescription: 'BBBBBB',
            serviceStartDate: currentDate.format(DATE_FORMAT),
            serviceEndDate: currentDate.format(DATE_FORMAT),
            servicePrice: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            serviceStartDate: currentDate,
            serviceEndDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Service', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
