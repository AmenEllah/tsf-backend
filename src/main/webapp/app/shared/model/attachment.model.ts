import { AttachmentType } from 'app/shared/model/enumerations/attachment-type.model';
import { AttachmentStatus } from 'app/shared/model/enumerations/attachment-status.model';

export interface IAttachment {
  id?: number;
  name?: string;
  path?: string;
  attachmentType?: AttachmentType;
  fileName?: string;
  status?: AttachmentStatus;
  requestId?: number;
}

export class Attachment implements IAttachment {
  constructor(
    public id?: number,
    public name?: string,
    public path?: string,
    public attachmentType?: AttachmentType,
    public fileName?: string,
    public status?: AttachmentStatus,
    public requestId?: number
  ) {}
}
