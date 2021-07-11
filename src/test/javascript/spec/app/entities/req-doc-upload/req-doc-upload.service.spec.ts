import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT, DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ReqDocUploadService } from 'app/entities/req-doc-upload/req-doc-upload.service';
import { IReqDocUpload, ReqDocUpload } from 'app/shared/model/req-doc-upload.model';
import { DocStatus } from 'app/shared/model/enumerations/doc-status.model';

describe('Service Tests', () => {
  describe('ReqDocUpload Service', () => {
    let injector: TestBed;
    let service: ReqDocUploadService;
    let httpMock: HttpTestingController;
    let elemDefault: IReqDocUpload;
    let expectedResult: IReqDocUpload | IReqDocUpload[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ReqDocUploadService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new ReqDocUpload(0, 'AAAAAAA', 'AAAAAAA', currentDate, currentDate, DocStatus.VALID);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            uploadedAt: currentDate.format(DATE_FORMAT),
            updatedAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ReqDocUpload', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            uploadedAt: currentDate.format(DATE_FORMAT),
            updatedAt: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            uploadedAt: currentDate,
            updatedAt: currentDate,
          },
          returnedFromService
        );

        service.create(new ReqDocUpload()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ReqDocUpload', () => {
        const returnedFromService = Object.assign(
          {
            pathDoc: 'BBBBBB',
            typeDoc: 'BBBBBB',
            uploadedAt: currentDate.format(DATE_FORMAT),
            updatedAt: currentDate.format(DATE_TIME_FORMAT),
            docStatus: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            uploadedAt: currentDate,
            updatedAt: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ReqDocUpload', () => {
        const returnedFromService = Object.assign(
          {
            pathDoc: 'BBBBBB',
            typeDoc: 'BBBBBB',
            uploadedAt: currentDate.format(DATE_FORMAT),
            updatedAt: currentDate.format(DATE_TIME_FORMAT),
            docStatus: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            uploadedAt: currentDate,
            updatedAt: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a ReqDocUpload', () => {
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
