import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISubcontractingStaff, SubcontractingStaff } from 'app/shared/model/subcontracting-staff.model';
import { SubcontractingStaffService } from './subcontracting-staff.service';
import { SubcontractingStaffComponent } from './subcontracting-staff.component';
import { SubcontractingStaffDetailComponent } from './subcontracting-staff-detail.component';
import { SubcontractingStaffUpdateComponent } from './subcontracting-staff-update.component';

@Injectable({ providedIn: 'root' })
export class SubcontractingStaffResolve implements Resolve<ISubcontractingStaff> {
  constructor(private service: SubcontractingStaffService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISubcontractingStaff> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((subcontractingStaff: HttpResponse<SubcontractingStaff>) => {
          if (subcontractingStaff.body) {
            return of(subcontractingStaff.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SubcontractingStaff());
  }
}

export const subcontractingStaffRoute: Routes = [
  {
    path: '',
    component: SubcontractingStaffComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.subcontractingStaff.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SubcontractingStaffDetailComponent,
    resolve: {
      subcontractingStaff: SubcontractingStaffResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.subcontractingStaff.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SubcontractingStaffUpdateComponent,
    resolve: {
      subcontractingStaff: SubcontractingStaffResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.subcontractingStaff.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SubcontractingStaffUpdateComponent,
    resolve: {
      subcontractingStaff: SubcontractingStaffResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.subcontractingStaff.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
