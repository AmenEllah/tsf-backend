import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDetailOffer, DetailOffer } from 'app/shared/model/detail-offer.model';
import { DetailOfferService } from './detail-offer.service';
import { DetailOfferComponent } from './detail-offer.component';
import { DetailOfferDetailComponent } from './detail-offer-detail.component';
import { DetailOfferUpdateComponent } from './detail-offer-update.component';

@Injectable({ providedIn: 'root' })
export class DetailOfferResolve implements Resolve<IDetailOffer> {
  constructor(private service: DetailOfferService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDetailOffer> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((detailOffer: HttpResponse<DetailOffer>) => {
          if (detailOffer.body) {
            return of(detailOffer.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DetailOffer());
  }
}

export const detailOfferRoute: Routes = [
  {
    path: '',
    component: DetailOfferComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.detailOffer.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DetailOfferDetailComponent,
    resolve: {
      detailOffer: DetailOfferResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.detailOffer.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DetailOfferUpdateComponent,
    resolve: {
      detailOffer: DetailOfferResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.detailOffer.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DetailOfferUpdateComponent,
    resolve: {
      detailOffer: DetailOfferResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.detailOffer.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
