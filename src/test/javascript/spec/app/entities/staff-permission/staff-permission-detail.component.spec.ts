import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { StaffPermissionDetailComponent } from 'app/entities/staff-permission/staff-permission-detail.component';
import { StaffPermission } from 'app/shared/model/staff-permission.model';

describe('Component Tests', () => {
  describe('StaffPermission Management Detail Component', () => {
    let comp: StaffPermissionDetailComponent;
    let fixture: ComponentFixture<StaffPermissionDetailComponent>;
    const route = ({ data: of({ staffPermission: new StaffPermission(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [StaffPermissionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(StaffPermissionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(StaffPermissionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load staffPermission on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.staffPermission).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
