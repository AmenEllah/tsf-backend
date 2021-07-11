import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFinancialInfo, FinancialInfo } from 'app/shared/model/financial-info.model';
import { FinancialInfoService } from './financial-info.service';
import { FinancialInfoComponent } from './financial-info.component';
import { FinancialInfoDetailComponent } from './financial-info-detail.component';
import { FinancialInfoUpdateComponent } from './financial-info-update.component';

@Injectable({ providedIn: 'root' })
export class FinancialInfoResolve implements Resolve<IFinancialInfo> {
  constructor(private service: FinancialInfoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFinancialInfo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((financialInfo: HttpResponse<FinancialInfo>) => {
          if (financialInfo.body) {
            return of(financialInfo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FinancialInfo());
  }
}

export const financialInfoRoute: Routes = [
  {
    path: '',
    component: FinancialInfoComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.financialInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FinancialInfoDetailComponent,
    resolve: {
      financialInfo: FinancialInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.financialInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FinancialInfoUpdateComponent,
    resolve: {
      financialInfo: FinancialInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.financialInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FinancialInfoUpdateComponent,
    resolve: {
      financialInfo: FinancialInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.financialInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
