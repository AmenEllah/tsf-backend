import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAppSettings } from 'app/shared/model/app-settings.model';

@Component({
  selector: 'jhi-app-settings-detail',
  templateUrl: './app-settings-detail.component.html',
})
export class AppSettingsDetailComponent implements OnInit {
  appSettings: IAppSettings | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ appSettings }) => (this.appSettings = appSettings));
  }

  previousState(): void {
    window.history.back();
  }
}
