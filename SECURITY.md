# Security Policy

## Automated security scanning

This project runs layered, open-source security tooling in CI (GitHub Actions):

| Layer | Tool | Workflow | Output |
|-------|------|----------|--------|
| SAST (code) | **CodeQL** (`java-kotlin`, security-and-quality) | `codeql.yml` | Security tab (code scanning alerts) |
| SAST (mobile) | **MobSF · mobsfscan** | `security-scan.yml` | SARIF → Security tab |
| Static APK analysis | **MobSF** (full, via Docker REST API) | `security-scan.yml` | JSON report artifact |
| Secrets | **gitleaks** (full history) | `security-scan.yml` | Job status |
| Dependencies | **Dependabot** + **dependency-review** + Gradle dependency submission | `dependabot.yml`, `security-scan.yml`, `dependency-submission.yml` | Alerts / PR checks |
| Lint/quality | **detekt**, **ktlint**, **Android Lint** | `code-quality.yml`, `android-ci.yml` | SARIF / reports |

## Dynamic analysis (DAST)

MobSF dynamic analysis requires a live Android device/emulator attached to a
MobSF instance. GitHub-hosted runners don't provide the nested virtualization
needed for a reliable emulator + MobSF dynamic analyzer, so **DAST is run
locally/manually**, not in CI:

```bash
# 1. Start MobSF (with access to a running emulator/Genymotion/AVD)
docker run --rm -it -p 8000:8000 \
  opensecurity/mobile-security-framework-mobsf:latest

# 2. Open http://localhost:8000, upload the APK, and use the
#    "Dynamic Analyzer" against your connected device/emulator.
```

See the MobSF docs for device setup: https://mobsf.github.io/docs/

## Reporting a vulnerability

Please open a private security advisory via the repository's **Security →
Advisories** tab, or contact the maintainer directly. Do not file public issues
for security-sensitive reports.

## Supported versions

The latest release on `master` is supported. Security fixes are applied to the
current line only.
