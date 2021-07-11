import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAgency, Agency } from 'app/shared/model/agency.model';
import { AgencyService } from './agency.service';
import { AgencyComponent } from './agency.component';
import { AgencyDetailComponent } from './agency-detail.component';
import { AgencyUpdateComponent } from './agency-update.component';

@Injectable({ providedIn: 'root' })
export class AgencyResolve implements Resolve<IAgency> {
  constructor(private service: AgencyService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAgency> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((agency: HttpResponse<Agency>) => {
          if (agency.body) {
            return of(agency.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Agency());
  }
}

export const agencyRoute: Routes = [
  {
    path: '',
    component: AgencyComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.agency.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AgencyDetailComponent,
    resolve: {
      agency: AgencyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.agency.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AgencyUpdateComponent,
    resolve: {
      agency: AgencyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.agency.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AgencyUpdateComponent,
    resolve: {
      agency: AgencyResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.agency.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
