import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TfsBackendTestModule } from '../../../test.module';
import { SubscriberStatusDetailComponent } from 'app/entities/subscriber-status/subscriber-status-detail.component';
import { SubscriberStatus } from 'app/shared/model/subscriber-status.model';

describe('Component Tests', () => {
  describe('SubscriberStatus Management Detail Component', () => {
    let comp: SubscriberStatusDetailComponent;
    let fixture: ComponentFixture<SubscriberStatusDetailComponent>;
    const route = ({ data: of({ subscriberStatus: new SubscriberStatus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TfsBackendTestModule],
        declarations: [SubscriberStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SubscriberStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SubscriberStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load subscriberStatus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.subscriberStatus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
