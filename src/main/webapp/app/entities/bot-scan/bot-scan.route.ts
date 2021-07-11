import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBotScan, BotScan } from 'app/shared/model/bot-scan.model';
import { BotScanService } from './bot-scan.service';
import { BotScanComponent } from './bot-scan.component';
import { BotScanDetailComponent } from './bot-scan-detail.component';
import { BotScanUpdateComponent } from './bot-scan-update.component';

@Injectable({ providedIn: 'root' })
export class BotScanResolve implements Resolve<IBotScan> {
  constructor(private service: BotScanService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBotScan> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((botScan: HttpResponse<BotScan>) => {
          if (botScan.body) {
            return of(botScan.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BotScan());
  }
}

export const botScanRoute: Routes = [
  {
    path: '',
    component: BotScanComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.botScan.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BotScanDetailComponent,
    resolve: {
      botScan: BotScanResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.botScan.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BotScanUpdateComponent,
    resolve: {
      botScan: BotScanResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.botScan.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BotScanUpdateComponent,
    resolve: {
      botScan: BotScanResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.botScan.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
