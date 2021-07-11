import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SubscriberStatusService } from 'app/entities/subscriber-status/subscriber-status.service';
import { ISubscriberStatus, SubscriberStatus } from 'app/shared/model/subscriber-status.model';
import { appelVisioStatus } from 'app/shared/model/enumerations/appel-visio-status.model';
import { withCertifStatus } from 'app/shared/model/enumerations/with-certif-status.model';
import { withSignatureStatus } from 'app/shared/model/enumerations/with-signature-status.model';

describe('Service Tests', () => {
  describe('SubscriberStatus Service', () => {
    let injector: TestBed;
    let service: SubscriberStatusService;
    let httpMock: HttpTestingController;
    let elemDefault: ISubscriberStatus;
    let expectedResult: ISubscriberStatus | ISubscriberStatus[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SubscriberStatusService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new SubscriberStatus(
        0,
        'AAAAAAA',
        'AAAAAAA',
        appelVisioStatus.O,
        withCertifStatus.O,
        withSignatureStatus.O,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateAppelVisio: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SubscriberStatus', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateAppelVisio: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAppelVisio: currentDate,
          },
          returnedFromService
        );

        service.create(new SubscriberStatus()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SubscriberStatus', () => {
        const returnedFromService = Object.assign(
          {
            email: 'BBBBBB',
            numCin: 'BBBBBB',
            withAppelVisio: 'BBBBBB',
            withCertif: 'BBBBBB',
            withSignature: 'BBBBBB',
            dateAppelVisio: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAppelVisio: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SubscriberStatus', () => {
        const returnedFromService = Object.assign(
          {
            email: 'BBBBBB',
            numCin: 'BBBBBB',
            withAppelVisio: 'BBBBBB',
            withCertif: 'BBBBBB',
            withSignature: 'BBBBBB',
            dateAppelVisio: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateAppelVisio: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a SubscriberStatus', () => {
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
