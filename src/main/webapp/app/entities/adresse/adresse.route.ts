import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAdresse, Adresse } from 'app/shared/model/adresse.model';
import { AdresseService } from './adresse.service';
import { AdresseComponent } from './adresse.component';
import { AdresseDetailComponent } from './adresse-detail.component';
import { AdresseUpdateComponent } from './adresse-update.component';

@Injectable({ providedIn: 'root' })
export class AdresseResolve implements Resolve<IAdresse> {
  constructor(private service: AdresseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAdresse> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((adresse: HttpResponse<Adresse>) => {
          if (adresse.body) {
            return of(adresse.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Adresse());
  }
}

export const adresseRoute: Routes = [
  {
    path: '',
    component: AdresseComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.adresse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AdresseDetailComponent,
    resolve: {
      adresse: AdresseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.adresse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AdresseUpdateComponent,
    resolve: {
      adresse: AdresseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.adresse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AdresseUpdateComponent,
    resolve: {
      adresse: AdresseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.adresse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
