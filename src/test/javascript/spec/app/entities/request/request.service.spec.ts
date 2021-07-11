import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { RequestService } from 'app/entities/request/request.service';
import { IRequest, Request } from 'app/shared/model/request.model';
import { StateRequest } from 'app/shared/model/enumerations/state-request.model';
import { RequestStatus } from 'app/shared/model/enumerations/request-status.model';

describe('Service Tests', () => {
  describe('Request Service', () => {
    let injector: TestBed;
    let service: RequestService;
    let httpMock: HttpTestingController;
    let elemDefault: IRequest;
    let expectedResult: IRequest | IRequest[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RequestService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Request(0, currentDate, currentDate, false, 'AAAAAAA', 'AAAAAAA', 0, StateRequest.VISIO, RequestStatus.PENDING);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            visioDate: currentDate.format(DATE_FORMAT),
            sendingMailDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Request', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            visioDate: currentDate.format(DATE_FORMAT),
            sendingMailDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            visioDate: currentDate,
            sendingMailDate: currentDate,
          },
          returnedFromService
        );

        service.create(new Request()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Request', () => {
        const returnedFromService = Object.assign(
          {
            visioDate: currentDate.format(DATE_FORMAT),
            sendingMailDate: currentDate.format(DATE_FORMAT),
            state: true,
            step: 'BBBBBB',
            codeVerification: 'BBBBBB',
            amplitudeRef: 1,
            requestState: 'BBBBBB',
            requestStatus: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            visioDate: currentDate,
            sendingMailDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Request', () => {
        const returnedFromService = Object.assign(
          {
            visioDate: currentDate.format(DATE_FORMAT),
            sendingMailDate: currentDate.format(DATE_FORMAT),
            state: true,
            step: 'BBBBBB',
            codeVerification: 'BBBBBB',
            amplitudeRef: 1,
            requestState: 'BBBBBB',
            requestStatus: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            visioDate: currentDate,
            sendingMailDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Request', () => {
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
