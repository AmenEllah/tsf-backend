import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAppSettings, AppSettings } from 'app/shared/model/app-settings.model';
import { AppSettingsService } from './app-settings.service';
import { AppSettingsComponent } from './app-settings.component';
import { AppSettingsDetailComponent } from './app-settings-detail.component';
import { AppSettingsUpdateComponent } from './app-settings-update.component';

@Injectable({ providedIn: 'root' })
export class AppSettingsResolve implements Resolve<IAppSettings> {
  constructor(private service: AppSettingsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAppSettings> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((appSettings: HttpResponse<AppSettings>) => {
          if (appSettings.body) {
            return of(appSettings.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AppSettings());
  }
}

export const appSettingsRoute: Routes = [
  {
    path: '',
    component: AppSettingsComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'tfsBackendApp.appSettings.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AppSettingsDetailComponent,
    resolve: {
      appSettings: AppSettingsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.appSettings.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AppSettingsUpdateComponent,
    resolve: {
      appSettings: AppSettingsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.appSettings.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AppSettingsUpdateComponent,
    resolve: {
      appSettings: AppSettingsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'tfsBackendApp.appSettings.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
