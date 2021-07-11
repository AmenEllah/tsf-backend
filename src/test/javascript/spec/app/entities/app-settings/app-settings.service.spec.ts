import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AppSettingsService } from 'app/entities/app-settings/app-settings.service';
import { IAppSettings, AppSettings } from 'app/shared/model/app-settings.model';
import { SenderProvider } from 'app/shared/model/enumerations/sender-provider.model';
import { SignerProvider } from 'app/shared/model/enumerations/signer-provider.model';

describe('Service Tests', () => {
  describe('AppSettings Service', () => {
    let injector: TestBed;
    let service: AppSettingsService;
    let httpMock: HttpTestingController;
    let elemDefault: IAppSettings;
    let expectedResult: IAppSettings | IAppSettings[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AppSettingsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new AppSettings(0, SenderProvider.MIDDLEWARE, SignerProvider.NG_SIGN);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AppSettings', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new AppSettings()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AppSettings', () => {
        const returnedFromService = Object.assign(
          {
            mailSenderProvider: 'BBBBBB',
            signerProvider: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AppSettings', () => {
        const returnedFromService = Object.assign(
          {
            mailSenderProvider: 'BBBBBB',
            signerProvider: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a AppSettings', () => {
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
