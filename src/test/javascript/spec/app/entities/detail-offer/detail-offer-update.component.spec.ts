import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { DetailOfferUpdateComponent } from 'app/entities/detail-offer/detail-offer-update.component';
import { DetailOfferService } from 'app/entities/detail-offer/detail-offer.service';
import { DetailOffer } from 'app/shared/model/detail-offer.model';

describe('Component Tests', () => {
  describe('DetailOffer Management Update Component', () => {
    let comp: DetailOfferUpdateComponent;
    let fixture: ComponentFixture<DetailOfferUpdateComponent>;
    let service: DetailOfferService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [DetailOfferUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DetailOfferUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DetailOfferUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DetailOfferService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DetailOffer(123);
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
        const entity = new DetailOffer();
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
