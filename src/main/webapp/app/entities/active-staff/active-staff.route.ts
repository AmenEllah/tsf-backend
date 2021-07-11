import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IActiveStaff, ActiveStaff } from 'app/shared/model/active-staff.model';
import { ActiveStaffService } from './active-staff.service';
import { ActiveStaffComponent } from './active-staff.component';
import { ActiveStaffDetailComponent } from './active-staff-detail.component';
import { ActiveStaffUpdateComponent } from './active-staff-update.component';

@Injectable({ providedIn: 'root' })
export class ActiveStaffResolve implements Resolve<IActiveStaff> {
  constructor(private service: ActiveStaffService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IActiveStaff> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((activeStaff: HttpResponse<ActiveStaff>) => {
          if (activeStaff.body) {
            return of(activeStaff.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ActiveStaff());
  }
}

export const activeStaffRoute: Routes = [
  {
    path: '',
    component: ActiveStaffComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.activeStaff.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ActiveStaffDetailComponent,
    resolve: {
      activeStaff: ActiveStaffResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.activeStaff.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ActiveStaffUpdateComponent,
    resolve: {
      activeStaff: ActiveStaffResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.activeStaff.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ActiveStaffUpdateComponent,
    resolve: {
      activeStaff: ActiveStaffResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.activeStaff.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
