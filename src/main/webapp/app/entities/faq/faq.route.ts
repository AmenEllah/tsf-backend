import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFAQ, FAQ } from 'app/shared/model/faq.model';
import { FAQService } from './faq.service';
import { FAQComponent } from './faq.component';
import { FAQDetailComponent } from './faq-detail.component';
import { FAQUpdateComponent } from './faq-update.component';

@Injectable({ providedIn: 'root' })
export class FAQResolve implements Resolve<IFAQ> {
  constructor(private service: FAQService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFAQ> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((fAQ: HttpResponse<FAQ>) => {
          if (fAQ.body) {
            return of(fAQ.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FAQ());
  }
}

export const fAQRoute: Routes = [
  {
    path: '',
    component: FAQComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.fAQ.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FAQDetailComponent,
    resolve: {
      fAQ: FAQResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.fAQ.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FAQUpdateComponent,
    resolve: {
      fAQ: FAQResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.fAQ.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FAQUpdateComponent,
    resolve: {
      fAQ: FAQResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.fAQ.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
