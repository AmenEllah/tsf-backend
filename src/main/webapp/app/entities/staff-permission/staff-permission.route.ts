import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IStaffPermission, StaffPermission } from 'app/shared/model/staff-permission.model';
import { StaffPermissionService } from './staff-permission.service';
import { StaffPermissionComponent } from './staff-permission.component';
import { StaffPermissionDetailComponent } from './staff-permission-detail.component';
import { StaffPermissionUpdateComponent } from './staff-permission-update.component';

@Injectable({ providedIn: 'root' })
export class StaffPermissionResolve implements Resolve<IStaffPermission> {
  constructor(private service: StaffPermissionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStaffPermission> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((staffPermission: HttpResponse<StaffPermission>) => {
          if (staffPermission.body) {
            return of(staffPermission.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new StaffPermission());
  }
}

export const staffPermissionRoute: Routes = [
  {
    path: '',
    component: StaffPermissionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.staffPermission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StaffPermissionDetailComponent,
    resolve: {
      staffPermission: StaffPermissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.staffPermission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StaffPermissionUpdateComponent,
    resolve: {
      staffPermission: StaffPermissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.staffPermission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StaffPermissionUpdateComponent,
    resolve: {
      staffPermission: StaffPermissionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.staffPermission.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
