import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAdressInfo, AdressInfo } from 'app/shared/model/adress-info.model';
import { AdressInfoService } from './adress-info.service';
import { AdressInfoComponent } from './adress-info.component';
import { AdressInfoDetailComponent } from './adress-info-detail.component';
import { AdressInfoUpdateComponent } from './adress-info-update.component';

@Injectable({ providedIn: 'root' })
export class AdressInfoResolve implements Resolve<IAdressInfo> {
  constructor(private service: AdressInfoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAdressInfo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((adressInfo: HttpResponse<AdressInfo>) => {
          if (adressInfo.body) {
            return of(adressInfo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AdressInfo());
  }
}

export const adressInfoRoute: Routes = [
  {
    path: '',
    component: AdressInfoComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.adressInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AdressInfoDetailComponent,
    resolve: {
      adressInfo: AdressInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.adressInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AdressInfoUpdateComponent,
    resolve: {
      adressInfo: AdressInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.adressInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AdressInfoUpdateComponent,
    resolve: {
      adressInfo: AdressInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.adressInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
