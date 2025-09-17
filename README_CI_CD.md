## CI/CD for Android (Jetpack Compose)

This repository includes GitHub Actions workflows to build, test, lint, and produce artifacts for your Android app.

### CI (Pull Requests and Pushes)
- Workflow: `.github/workflows/android-ci.yml`
- Runs on pushes and PRs to `main`:
  - Build debug APK
  - Run unit tests (debug)
  - Run Android Lint (debug)
  - Upload APK and reports as artifacts

### CD (Tagged Releases)
- Workflow: `.github/workflows/android-cd.yml`
- Triggers on tags matching `v*` (e.g. `v1.0.0`) or manual dispatch.
- Produces:
  - `app-release.apk`
  - `app-release.aab`
- Attaches artifacts to the GitHub Release created from the tag.

### Optional Signing (CD)
If you already configure signing in Gradle, nothing else is required. If you prefer CI-side signing, provide these repository secrets and the CD workflow will sign artifacts when present:

- `ANDROID_KEYSTORE_BASE64`: Base64-encoded keystore file
- `ANDROID_KEYSTORE_PASSWORD`: Keystore password
- `ANDROID_KEY_ALIAS`: Key alias
- `ANDROID_KEY_PASSWORD`: Key password

Encode keystore:
```bash
base64 -i your-release.keystore | pbcopy
```

Then add to GitHub Repo Settings → Secrets and variables → Actions.

### Play Store Publish (Optional)
Uncomment the Google Play step in `.github/workflows/android-cd.yml` and set:

- `PLAY_SERVICE_ACCOUNT_JSON`: Contents of the service account JSON
- `packageName`: Your applicationId (update in the workflow step)

### Notes
- JDK 17 is used. Adjust if your Gradle/AGP requires a different version.
- Workflows rely on Gradle Wrapper and standard module `:app` tasks. If your module name differs, update tasks accordingly.


