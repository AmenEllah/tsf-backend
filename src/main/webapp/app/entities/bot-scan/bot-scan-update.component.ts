import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBotScan, BotScan } from 'app/shared/model/bot-scan.model';
import { BotScanService } from './bot-scan.service';

@Component({
  selector: 'jhi-bot-scan-update',
  templateUrl: './bot-scan-update.component.html',
})
export class BotScanUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    ref_demande: [],
    cliDelta: [],
    signature: [],
    compte: [],
  });

  constructor(protected botScanService: BotScanService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ botScan }) => {
      this.updateForm(botScan);
    });
  }

  updateForm(botScan: IBotScan): void {
    this.editForm.patchValue({
      id: botScan.id,
      ref_demande: botScan.ref_demande,
      cliDelta: botScan.cliDelta,
      signature: botScan.signature,
      compte: botScan.compte,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const botScan = this.createFromForm();
    if (botScan.id !== undefined) {
      this.subscribeToSaveResponse(this.botScanService.update(botScan));
    } else {
      this.subscribeToSaveResponse(this.botScanService.create(botScan));
    }
  }

  private createFromForm(): IBotScan {
    return {
      ...new BotScan(),
      id: this.editForm.get(['id'])!.value,
      ref_demande: this.editForm.get(['ref_demande'])!.value,
      cliDelta: this.editForm.get(['cliDelta'])!.value,
      signature: this.editForm.get(['signature'])!.value,
      compte: this.editForm.get(['compte'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBotScan>>): void {
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
