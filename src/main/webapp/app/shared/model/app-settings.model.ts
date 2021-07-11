import { SenderProvider } from 'app/shared/model/enumerations/sender-provider.model';
import { SignerProvider } from 'app/shared/model/enumerations/signer-provider.model';

export interface IAppSettings {
  id?: number;
  mailSenderProvider?: SenderProvider;
  signerProvider?: SignerProvider;
}

export class AppSettings implements IAppSettings {
  constructor(public id?: number, public mailSenderProvider?: SenderProvider, public signerProvider?: SignerProvider) {}
}
