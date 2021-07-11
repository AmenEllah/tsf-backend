import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { ActiveStaffDetailComponent } from 'app/entities/active-staff/active-staff-detail.component';
import { ActiveStaff } from 'app/shared/model/active-staff.model';

describe('Component Tests', () => {
  describe('ActiveStaff Management Detail Component', () => {
    let comp: ActiveStaffDetailComponent;
    let fixture: ComponentFixture<ActiveStaffDetailComponent>;
    const route = ({ data: of({ activeStaff: new ActiveStaff(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [ActiveStaffDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ActiveStaffDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ActiveStaffDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load activeStaff on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.activeStaff).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
