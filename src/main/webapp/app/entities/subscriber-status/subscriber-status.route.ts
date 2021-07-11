import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISubscriberStatus, SubscriberStatus } from 'app/shared/model/subscriber-status.model';
import { SubscriberStatusService } from './subscriber-status.service';
import { SubscriberStatusComponent } from './subscriber-status.component';
import { SubscriberStatusDetailComponent } from './subscriber-status-detail.component';
import { SubscriberStatusUpdateComponent } from './subscriber-status-update.component';

@Injectable({ providedIn: 'root' })
export class SubscriberStatusResolve implements Resolve<ISubscriberStatus> {
  constructor(private service: SubscriberStatusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISubscriberStatus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((subscriberStatus: HttpResponse<SubscriberStatus>) => {
          if (subscriberStatus.body) {
            return of(subscriberStatus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SubscriberStatus());
  }
}

export const subscriberStatusRoute: Routes = [
  {
    path: '',
    component: SubscriberStatusComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.subscriberStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SubscriberStatusDetailComponent,
    resolve: {
      subscriberStatus: SubscriberStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.subscriberStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SubscriberStatusUpdateComponent,
    resolve: {
      subscriberStatus: SubscriberStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.subscriberStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SubscriberStatusUpdateComponent,
    resolve: {
      subscriberStatus: SubscriberStatusResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.subscriberStatus.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
