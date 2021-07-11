import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMunicipality, Municipality } from 'app/shared/model/municipality.model';
import { MunicipalityService } from './municipality.service';
import { MunicipalityComponent } from './municipality.component';
import { MunicipalityDetailComponent } from './municipality-detail.component';
import { MunicipalityUpdateComponent } from './municipality-update.component';

@Injectable({ providedIn: 'root' })
export class MunicipalityResolve implements Resolve<IMunicipality> {
  constructor(private service: MunicipalityService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMunicipality> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((municipality: HttpResponse<Municipality>) => {
          if (municipality.body) {
            return of(municipality.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Municipality());
  }
}

export const municipalityRoute: Routes = [
  {
    path: '',
    component: MunicipalityComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.municipality.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MunicipalityDetailComponent,
    resolve: {
      municipality: MunicipalityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.municipality.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MunicipalityUpdateComponent,
    resolve: {
      municipality: MunicipalityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.municipality.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MunicipalityUpdateComponent,
    resolve: {
      municipality: MunicipalityResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.municipality.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
