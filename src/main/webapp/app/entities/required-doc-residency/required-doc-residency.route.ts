import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRequiredDocResidency, RequiredDocResidency } from 'app/shared/model/required-doc-residency.model';
import { RequiredDocResidencyService } from './required-doc-residency.service';
import { RequiredDocResidencyComponent } from './required-doc-residency.component';
import { RequiredDocResidencyDetailComponent } from './required-doc-residency-detail.component';
import { RequiredDocResidencyUpdateComponent } from './required-doc-residency-update.component';

@Injectable({ providedIn: 'root' })
export class RequiredDocResidencyResolve implements Resolve<IRequiredDocResidency> {
  constructor(private service: RequiredDocResidencyService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRequiredDocResidency> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((requiredDocResidency: HttpResponse<RequiredDocResidency>) => {
          if (requiredDocResidency.body) {
            return of(requiredDocResidency.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RequiredDocResidency());
  }
}

export const requiredDocResidencyRoute: Routes = [
  {
    path: '',
    component: RequiredDocResidencyComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.requiredDocResidency.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RequiredDocResidencyDetailComponent,
    resolve: {
      requiredDocResidency: RequiredDocResidencyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.requiredDocResidency.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RequiredDocResidencyUpdateComponent,
    resolve: {
      requiredDocResidency: RequiredDocResidencyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.requiredDocResidency.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RequiredDocResidencyUpdateComponent,
    resolve: {
      requiredDocResidency: RequiredDocResidencyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.requiredDocResidency.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
