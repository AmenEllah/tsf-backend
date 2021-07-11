import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INationality, Nationality } from 'app/shared/model/nationality.model';
import { NationalityService } from './nationality.service';
import { NationalityComponent } from './nationality.component';
import { NationalityDetailComponent } from './nationality-detail.component';
import { NationalityUpdateComponent } from './nationality-update.component';

@Injectable({ providedIn: 'root' })
export class NationalityResolve implements Resolve<INationality> {
  constructor(private service: NationalityService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INationality> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((nationality: HttpResponse<Nationality>) => {
          if (nationality.body) {
            return of(nationality.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Nationality());
  }
}

export const nationalityRoute: Routes = [
  {
    path: '',
    component: NationalityComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.nationality.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NationalityDetailComponent,
    resolve: {
      nationality: NationalityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.nationality.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NationalityUpdateComponent,
    resolve: {
      nationality: NationalityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.nationality.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NationalityUpdateComponent,
    resolve: {
      nationality: NationalityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.nationality.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
