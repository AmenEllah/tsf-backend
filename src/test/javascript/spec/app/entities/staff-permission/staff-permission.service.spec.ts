import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { StaffPermissionService } from 'app/entities/staff-permission/staff-permission.service';
import { IStaffPermission, StaffPermission } from 'app/shared/model/staff-permission.model';
import { ActionPermission } from 'app/shared/model/enumerations/action-permission.model';
import { ScopePermission } from 'app/shared/model/enumerations/scope-permission.model';
import { StaffType } from 'app/shared/model/enumerations/staff-type.model';

describe('Service Tests', () => {
  describe('StaffPermission Service', () => {
    let injector: TestBed;
    let service: StaffPermissionService;
    let httpMock: HttpTestingController;
    let elemDefault: IStaffPermission;
    let expectedResult: IStaffPermission | IStaffPermission[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(StaffPermissionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new StaffPermission(0, 0, 0, ActionPermission.MODIFYING, ScopePermission.ALL, StaffType.ADVISOR_UNIT);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a StaffPermission', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new StaffPermission()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a StaffPermission', () => {
        const returnedFromService = Object.assign(
          {
            idRole: 1,
            idBu: 1,
            action: 'BBBBBB',
            scopePermission: 'BBBBBB',
            staffType: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of StaffPermission', () => {
        const returnedFromService = Object.assign(
          {
            idRole: 1,
            idBu: 1,
            action: 'BBBBBB',
            scopePermission: 'BBBBBB',
            staffType: 'BBBBBB',
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

      it('should delete a StaffPermission', () => {
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
