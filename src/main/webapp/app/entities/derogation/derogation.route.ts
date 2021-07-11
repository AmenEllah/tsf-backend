import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDerogation, Derogation } from 'app/shared/model/derogation.model';
import { DerogationService } from './derogation.service';
import { DerogationComponent } from './derogation.component';
import { DerogationDetailComponent } from './derogation-detail.component';
import { DerogationUpdateComponent } from './derogation-update.component';

@Injectable({ providedIn: 'root' })
export class DerogationResolve implements Resolve<IDerogation> {
  constructor(private service: DerogationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDerogation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((derogation: HttpResponse<Derogation>) => {
          if (derogation.body) {
            return of(derogation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Derogation());
  }
}

export const derogationRoute: Routes = [
  {
    path: '',
    component: DerogationComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.derogation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DerogationDetailComponent,
    resolve: {
      derogation: DerogationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.derogation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DerogationUpdateComponent,
    resolve: {
      derogation: DerogationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.derogation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DerogationUpdateComponent,
    resolve: {
      derogation: DerogationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.derogation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
