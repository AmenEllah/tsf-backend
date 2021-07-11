import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { AuthentitficationToSignUpdateComponent } from 'app/entities/authentitfication-to-sign/authentitfication-to-sign-update.component';
import { AuthentitficationToSignService } from 'app/entities/authentitfication-to-sign/authentitfication-to-sign.service';
import { AuthentitficationToSign } from 'app/shared/model/authentitfication-to-sign.model';

describe('Component Tests', () => {
  describe('AuthentitficationToSign Management Update Component', () => {
    let comp: AuthentitficationToSignUpdateComponent;
    let fixture: ComponentFixture<AuthentitficationToSignUpdateComponent>;
    let service: AuthentitficationToSignService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [AuthentitficationToSignUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AuthentitficationToSignUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AuthentitficationToSignUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AuthentitficationToSignService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AuthentitficationToSign(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new AuthentitficationToSign();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
