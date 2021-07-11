import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INationality, Nationality } from 'app/shared/model/nationality.model';
import { NationalityService } from './nationality.service';

@Component({
  selector: 'jhi-nationality-update',
  templateUrl: './nationality-update.component.html',
})
export class NationalityUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleFR: [],
    libelleEN: [],
    code: [],
    flag: [],
  });

  constructor(protected nationalityService: NationalityService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ nationality }) => {
      this.updateForm(nationality);
    });
  }

  updateForm(nationality: INationality): void {
    this.editForm.patchValue({
      id: nationality.id,
      libelleFR: nationality.libelleFR,
      libelleEN: nationality.libelleEN,
      code: nationality.code,
      flag: nationality.flag,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const nationality = this.createFromForm();
    if (nationality.id !== undefined) {
      this.subscribeToSaveResponse(this.nationalityService.update(nationality));
    } else {
      this.subscribeToSaveResponse(this.nationalityService.create(nationality));
    }
  }

  private createFromForm(): INationality {
    return {
      ...new Nationality(),
      id: this.editForm.get(['id'])!.value,
      libelleFR: this.editForm.get(['libelleFR'])!.value,
      libelleEN: this.editForm.get(['libelleEN'])!.value,
      code: this.editForm.get(['code'])!.value,
      flag: this.editForm.get(['flag'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INationality>>): void {
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
