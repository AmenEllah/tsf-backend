import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGovernorate, Governorate } from 'app/shared/model/governorate.model';
import { GovernorateService } from './governorate.service';
import { GovernorateComponent } from './governorate.component';
import { GovernorateDetailComponent } from './governorate-detail.component';
import { GovernorateUpdateComponent } from './governorate-update.component';

@Injectable({ providedIn: 'root' })
export class GovernorateResolve implements Resolve<IGovernorate> {
  constructor(private service: GovernorateService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGovernorate> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((governorate: HttpResponse<Governorate>) => {
          if (governorate.body) {
            return of(governorate.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Governorate());
  }
}

export const governorateRoute: Routes = [
  {
    path: '',
    component: GovernorateComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.governorate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GovernorateDetailComponent,
    resolve: {
      governorate: GovernorateResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.governorate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GovernorateUpdateComponent,
    resolve: {
      governorate: GovernorateResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.governorate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GovernorateUpdateComponent,
    resolve: {
      governorate: GovernorateResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.governorate.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
