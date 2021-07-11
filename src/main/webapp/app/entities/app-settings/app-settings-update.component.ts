import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAppSettings, AppSettings } from 'app/shared/model/app-settings.model';
import { AppSettingsService } from './app-settings.service';

@Component({
  selector: 'jhi-app-settings-update',
  templateUrl: './app-settings-update.component.html',
})
export class AppSettingsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    mailSenderProvider: [null, [Validators.required]],
    signerProvider: [null, [Validators.required]],
  });

  constructor(protected appSettingsService: AppSettingsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ appSettings }) => {
      this.updateForm(appSettings);
    });
  }

  updateForm(appSettings: IAppSettings): void {
    this.editForm.patchValue({
      id: appSettings.id,
      mailSenderProvider: appSettings.mailSenderProvider,
      signerProvider: appSettings.signerProvider,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const appSettings = this.createFromForm();
    if (appSettings.id !== undefined) {
      this.subscribeToSaveResponse(this.appSettingsService.update(appSettings));
    } else {
      this.subscribeToSaveResponse(this.appSettingsService.create(appSettings));
    }
  }

  private createFromForm(): IAppSettings {
    return {
      ...new AppSettings(),
      id: this.editForm.get(['id'])!.value,
      mailSenderProvider: this.editForm.get(['mailSenderProvider'])!.value,
      signerProvider: this.editForm.get(['signerProvider'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAppSettings>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
