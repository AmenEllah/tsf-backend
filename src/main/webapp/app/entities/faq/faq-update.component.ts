import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFAQ, FAQ } from 'app/shared/model/faq.model';
import { FAQService } from './faq.service';

@Component({
  selector: 'jhi-faq-update',
  templateUrl: './faq-update.component.html',
})
export class FAQUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    question: [],
    answer: [],
  });

  constructor(protected fAQService: FAQService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fAQ }) => {
      this.updateForm(fAQ);
    });
  }

  updateForm(fAQ: IFAQ): void {
    this.editForm.patchValue({
      id: fAQ.id,
      question: fAQ.question,
      answer: fAQ.answer,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fAQ = this.createFromForm();
    if (fAQ.id !== undefined) {
      this.subscribeToSaveResponse(this.fAQService.update(fAQ));
    } else {
      this.subscribeToSaveResponse(this.fAQService.create(fAQ));
    }
  }

  private createFromForm(): IFAQ {
    return {
      ...new FAQ(),
      id: this.editForm.get(['id'])!.value,
      question: this.editForm.get(['question'])!.value,
      answer: this.editForm.get(['answer'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFAQ>>): void {
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
